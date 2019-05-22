package com.nextyu.jenkins;

import cn.hutool.core.lang.Console;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JGitTest {

    @Test
    public void test() throws Exception {

        UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("xxx", "xxx");

        Map<String, Ref> map = Git.lsRemoteRepository()
                .setRemote("http://192.168.0.99/xxx.git")
                .setCredentialsProvider(credentialsProvider)
                .callAsMap();

        Console.log(map);

    }

    @Test
    public void test2() throws Exception{

        Git git = Git.open(new File("C:\\Users\\dev\\IdeaProjects\\xxx"));

        List<Ref> call = git.branchList().call();

        for (Ref ref : call) {
            System.out.println("Branch-Created: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }

        /*git.branchCreate().setName("mytest").call();

        call = git.branchList().call();

        for (Ref ref : call) {
            System.out.println("Branch-Created: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }*/

        git.branchDelete().setBranchNames("mytest").call();
    }

}
