package org.motechproject.deliverytools.jobhandlerinvoker;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.motechproject.deliverytools.jobhandlerinvoker.domain.MotechEventInvocation;
import org.motechproject.deliverytools.jobhandlerinvoker.domain.ScheduledJob;
import org.motechproject.deliverytools.jobhandlerinvoker.domain.ScheduledJobName;
import org.motechproject.scheduler.domain.MotechEvent;
import org.motechproject.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@RequestMapping("/motech-delivery-tools/jobhandler")
@Controller
public class ScheduledJobController {
    private ApplicationContext applicationContext;
    private AllScheduledJobs allScheduledJobs;
    private QueriedJobs queriedJobs;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public ScheduledJobController(ApplicationContext applicationContext, AllScheduledJobs allScheduledJobs, QueriedJobs queriedJobs) {
        this.applicationContext = applicationContext;
        this.allScheduledJobs = allScheduledJobs;
        this.queriedJobs = queriedJobs;
    }

    @RequestMapping(value = "invoke", method = RequestMethod.GET)
    @ResponseBody
    public String invoke(JobHandlerInvokeRequest request, HttpServletResponse response) {
        try {
            String className = request.className();
            String beanName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            Object bean = applicationContext.getBean(beanName);
            Method method = ReflectionUtils.findMethod(bean.getClass(), request.methodName(), MotechEvent.class);

            ScheduledJob scheduledJob = getScheduleJob(request, method);
            MotechEventInvocation invocation = new MotechEventInvocation(scheduledJob);
            ReflectionUtils.invokeMethod(method, bean, invocation.event());
            return "All Good";
        } catch (Throwable e) {
            response.setStatus(500);
            logger.error(String.format("Could not handle the request: %s", request.toString()), e);
            return ExceptionUtils.getStackTrace(e);
        }
    }

    private ScheduledJob getScheduleJob(JobHandlerInvokeRequest request, Method method) {
        ScheduledJobName scheduledJobName = new ScheduledJobName(request.jobId(), method);
        return allScheduledJobs.get(scheduledJobName.jobId(request.isRepeating()));
    }

    @RequestMapping(value = "exists", method = RequestMethod.GET)
    @ResponseBody 
    public String exists(JobHandlerInvokeRequest request, HttpServletResponse response) {
        String className = request.className();
        String beanName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
        Object bean = applicationContext.getBean(beanName);
        Method method = ReflectionUtils.findMethod(bean.getClass(), request.methodName(), MotechEvent.class);
        try {
            ScheduledJob scheduledJob = getScheduleJob(request, method);
            if (scheduledJob != null)
                return "true";
        } catch (IllegalArgumentException e) {
            if (e.getMessage().startsWith("No job named:")) return "false";
        }
        return "error";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public String list(TriggerListRequest request) throws Exception {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("NOW: ").append(DateUtil.now().toDate().toString()).append("<hr/>");
            String[] jobNames = queriedJobs.list();
            for (String jobName : jobNames) {
                ScheduledJob scheduledJob = allScheduledJobs.get(jobName);
                stringBuilder.append(String.format("<p>%s</p>", jobName));

                List<Date> triggerDateTimes = scheduledJob.triggerSummary(request.number());
                for (Date date : triggerDateTimes) {
                    stringBuilder.append(String.format("<p>%s</p>", date));
                }
                stringBuilder.append("<hr/>");
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            logger.error("Could not handle the request to list", e);
            return ExceptionUtils.getStackTrace(e);
        }
    }

    @RequestMapping(value = "clear", method = RequestMethod.GET)
    @ResponseBody
    public String clear() {
        try {
            queriedJobs.clear();
            return "All Good";
        } catch (Throwable e) {
            logger.error("Could not handle the request to clear", e);
            return ExceptionUtils.getStackTrace(e);
        }
    }
}
