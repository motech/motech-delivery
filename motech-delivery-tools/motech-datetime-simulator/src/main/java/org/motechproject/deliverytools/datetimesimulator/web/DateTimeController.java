package org.motechproject.deliverytools.datetimesimulator.web;

import org.apache.log4j.Logger;
import org.motechproject.deliverytools.datetimesimulator.domain.FlowingTimeMachine;
import org.motechproject.deliverytools.datetimesimulator.domain.FrozenTimeMachine;
import org.motechproject.deliverytools.datetimesimulator.domain.TimeMachine;
import org.motechproject.util.DateTimeSourceUtil;
import org.motechproject.util.DateUtil;
import org.motechproject.util.datetime.DefaultDateTimeSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/motech-delivery-tools/datetime")
@Controller
public class DateTimeController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @RequestMapping(value = "update", method = RequestMethod.GET)
    @ResponseBody
    public String update(@RequestParam String date, @RequestParam(defaultValue = "0") String hour, @RequestParam(defaultValue = "0") String minute, @RequestParam(required = false) String type, HttpServletResponse response) {
        try {
            TimeMachine sourceInstance = getTimeMachine(type);
            DateTimeSourceUtil.setSourceInstance(sourceInstance);
            sourceInstance.update(date, hour, minute);
            return String.format("Successfully set datetime to: %s", DateUtil.now());
        } catch (Exception e) {
            response.setStatus(500);
            logger.error(String.format("Could not set the datetime from Date=%s, Hour=%s, Minute=%s. Did you use something like: 2011-10-17", date, hour, minute), e);
            return e.toString();
        }
    }

    private TimeMachine getTimeMachine(String type) {
        if("flow".equals(type)){
            return new FlowingTimeMachine(new DefaultDateTimeSource());
        }
        return new FrozenTimeMachine(new DefaultDateTimeSource());
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return DateUtil.today().toString();
    }
}