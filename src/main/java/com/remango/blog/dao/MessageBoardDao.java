package com.remango.blog.dao;

import com.remango.blog.entity.MessageBoard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by li on 2019/3/27.
 */
public interface MessageBoardDao {

    void saveMessage(MessageBoard messageBoard);

    List<MessageBoard> queryMessageList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    int queryMessageBoardTotal();
}
