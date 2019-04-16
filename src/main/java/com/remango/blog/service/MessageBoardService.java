package com.remango.blog.service;

import com.remango.blog.entity.MessageBoard;
import com.remango.blog.entity.PageList;

/**
 * Created by li on 2019/3/27.
 */
public interface MessageBoardService {

    /**
     * 保存留言板
     * @param messageBoard 留言板对象
     */
    void saveMessage(MessageBoard messageBoard);

    /**
     * 根据模糊字段查询
     * @param offset 偏移量
     * @param limit 条数
     * @return 留言列表
     */
    PageList<MessageBoard> queryMessageList(Integer offset, Integer limit);
}
