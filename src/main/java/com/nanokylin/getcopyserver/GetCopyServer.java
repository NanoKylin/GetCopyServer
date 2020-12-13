package com.nanokylin.getcopyserver;

import com.nanokylin.getcopyserver.common.DependFile;
import com.nanokylin.getcopyserver.controller.MainController;
import com.nanokylin.getcopyserver.utils.FileUtil;

/**
 * Main Class
 * Stat of Dream
 * (Package) Main -> (controller) MainController
 * 当然交控制权之前检查能不能把MainController跑起来
 * 所以先检查依赖啦！
 *
 * @author Hanbings
 */
public class GetCopyServer {
    public static void main(String[] args) {
        // 文件工具类
        FileUtil fileUtil = new FileUtil();
        // 实例化文件列表
        DependFile configFileList = new DependFile();
        // 设置配置文件列表
        configFileList.setList();
        // 加载配置文件和依赖库
        fileUtil.existsFileComparedWithJar(configFileList.getList());
        // 实例化主控制器
        MainController mainController = new MainController();
        // 交控制权给控制器
        mainController.RunCatPawServer();
    }

}
