package com.quant.bridge.domain;


import com.quant.bridge.utils.Constants;

public class Message extends Object implements Cloneable {
    private String command;// 命令
    private String content;// 内容

    public Message() {
    }

    public Message(String command) {
        super();
        this.command = command;
    }

    public Message(String command, String content) {
        this.command = command;
        this.content = content;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        if (content != null) {
            return command + Constants.COMMAND_SEPARATOR + content;
        } else {
            return command;
        }
    }
}
