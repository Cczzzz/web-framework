package com.cc.redis.core.sentine;

/**
 * Created by chenchang on 2018/8/17.
 */
public interface SentinelEventHandler {
    String on_sdown = "+sdown";
    String down_sdown = "-sdown";
    String on_odown = "+odown";
    String down_odown = "-odown";
    String switch_master = "+switch-master";

    //给定的实例现在处于主观下线状态。
    void onSdown(String message);

    //给定的实例已经不再处于主观下线状态。
    void downSdown(String message);

    //给定的实例现在处于客观下线状态。
    void onOdown(String message);

    //给定的实例已经不再处于客观下线状态。
    void downOdown(String message);

    //配置变更，主服务器的 IP 和地址已经改变
    void switchMaster(String message);
}
