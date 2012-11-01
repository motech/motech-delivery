package org.motechproject.batch.utils;


import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class AntTask {

    private String buildFile;
    private String buildFilePath;
    private String environment;
    private String antFile;
    private boolean embed = false;

    public AntTask(String buildFile, String buildFilePath, String environment, String antFile) {
        this.buildFile = buildFile;
        this.buildFilePath = buildFilePath;
        this.environment = environment;
        this.antFile = antFile;
    }

    public AntTask(String buildFile, String buildFilePath, String environment, String antFile, boolean embed) {
        this(buildFile, buildFilePath, environment, antFile);
        this.embed = embed;
    }

    public int run(String targetName) throws IOException, InterruptedException {
        if (!embed)
            return runExploded(targetName);
        else
            return runEmbedded(targetName);
    }

    private int runEmbedded(String targetName) {
        Project project = new Project();
        ProjectHelper helper = ProjectHelper.getProjectHelper();
        project.addReference("ant.projectHelper", helper);
        URL file = this.getClass().getClassLoader().getResource(buildFile);
        if (null == file) {
            throw new RuntimeException("Could not find the build file in path");
        } else {
            File source = new File(file.getFile());
            DefaultLogger consoleLogger = initializeLogger();
            helper.parse(project, source);
            project.setBasedir(source.getParent());
            project.addBuildListener(consoleLogger);
            project.executeTarget(targetName);
            project.setProperty("env", environment);
        }
        return 0;
    }

    private int runExploded(String targetName) throws IOException, InterruptedException {
        String cmd = "sudo -E " + antFile + " -f " + buildFile + " " + targetName + " -Denv=" + environment + " -lib " + buildFilePath;
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        process = runtime.exec(cmd);
        process.waitFor();

        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = buf.readLine()) != null) {
            System.out.println(line);
        }

        return process.exitValue();
    }

    private DefaultLogger initializeLogger() {
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
        return consoleLogger;
    }
}
