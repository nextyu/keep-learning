package com.nextyu.jenkins;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.junit.Test;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JenkinsTest {

    private static final String SIT2_URL = "xxx";
    private static final String SIT1_URL = "xxx";

    @Test
    public void test() throws Exception {
        JenkinsServer jenkinsServer = new JenkinsServer(new URI(SIT2_URL), "xxx", "xxx");
        Map<String, Job> jobs = jenkinsServer.getJobs();

        jobs.forEach((k, v) -> {
            Console.log(k);
            Console.log(JSON.toJSONString(v));
        });

        JobWithDetails details = jobs.get("xxx").details();

        jobs.get("xxx").build();

    }

    @Test
    public void test2() throws Exception {
        JenkinsServer jenkinsServer = new JenkinsServer(new URI(SIT1_URL), "xxx", "xxx");
        Map<String, Job> jobs = jenkinsServer.getJobs();


        jobs.forEach((k, v) -> {
            Console.log(k);
            Console.log(JSON.toJSONString(v));
        });

        JobWithDetails jobDetails = jobs.get("xxx").details();
        Console.log(jobDetails);
        HashMap<String, String> params = Maps.newHashMap();
        params.put("commit", "yes");
//        jobDetails.build(params);


        BuildWithDetails buildDetails = jobDetails.getLastBuild().details();

        while (buildDetails.isBuilding()) {
            Console.log("构建中");
        }

        Console.log();

    }

    @Test
    public void test3() throws Exception{
        JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://localhost:8080"), "admin", "123456");
        Map<String, Job> jobs = jenkinsServer.getJobs();


        jobs.forEach((k, v) -> {
            Console.log(k);
            Console.log(JSON.toJSONString(v));
        });

        JobWithDetails jobDetails = jobs.get("test").details();
//        jobDetails.build(true);

        while (jenkinsServer.getJob("test").getLastBuild().details().isBuilding()) {
            Console.log("构建中");
            TimeUnit.SECONDS.sleep(3);
        }

        Console.log("构建完毕");

        ClassPathResource resource = new ClassPathResource("config.xml");



        String jobXml = FileUtil.readString(resource.getFile(), Charset.defaultCharset());

        jenkinsServer.createJob("test3", jobXml, true);
    }
}
