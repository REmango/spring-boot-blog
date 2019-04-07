package com.remango.blog.service;

import com.remango.blog.entity.MessageBoard;
import com.remango.blog.entity.PageList;

/**
 * Created by li on 2019/3/27.
 */
public interface MessageBoardService {

    void saveMessage(MessageBoard messageBoard);
    PageList<MessageBoard> queryMessageList(Integer offset, Integer limit);
}
