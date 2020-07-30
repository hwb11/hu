package com.zy.intelligentdevice.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 用于自动化
 * 打包前自动更新SVN
 */
public class SVNUtils {

    public static void main(String[] args) {

        String[] cmds = {"/bin/sh", "-c", "git -C /Users/duanhong/Documents/workspace2/sy-new-cloud pull"};
       // String[] cmds = {"/bin/sh", "-c", "svn update /Users/duanhong/Documents/workspace2/sy-new-cloud"};
        try {
            Process pro = Runtime.getRuntime().exec(cmds);

            InputStream in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }

            in.close();
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
