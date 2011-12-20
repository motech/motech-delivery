package org.motechproject.deliverytools.jobhandlerinvoker;

public class JobHandlerInvokeRequest {
    private String className;
    private String methodName;
    private String jobId;
    private boolean isRepeating;

    public String className() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String methodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String jobId() {
        return jobId;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public void setRepeating(boolean repeating) {
        isRepeating = repeating;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("JobHandlerInvokeRequest");
        sb.append("{className='").append(className).append('\'');
        sb.append(", methodName='").append(methodName).append('\'');
        sb.append(", jobId='").append(jobId).append('\'');
        sb.append(", isRepeating=").append(isRepeating);
        sb.append('}');
        return sb.toString();
    }
}