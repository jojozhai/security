/**
 *
 */
package com.imooc.security.rbac.web.controller.support;

/**
 * @author zhailiang
 *
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
