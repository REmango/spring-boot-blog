package com.remango.blog.service.impl;

import com.remango.blog.dao.MessageBoardDao;
import com.remango.blog.entity.MessageBoard;
import com.remango.blog.entity.PageList;
import com.remango.blog.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by li on 2019/3/27.
 */
@Service
public class MessageBoardServiceImpl  implements MessageBoardService {
    @Autowired
    private MessageBoardDao messageBoardDao;

    @Override
    public void saveMessage(MessageBoard messageBoard) {
        messageBoard.setCreateTime(new Date());
        messageBoardDao.saveMessage(messageBoard);
    }

    @Override
    public PageList<MessageBoard> queryMessageList(Integer offset, Integer limit){
        PageList<MessageBoard> messageBoardPageList = new PageList<>();
        List<MessageBoard> messageBoardList =  messageBoardDao.queryMessageList(offset, limit);
        int total = messageBoardDao.queryMessageBoardTotal();

        messageBoardPageList.setRows(messageBoardList);
        messageBoardPageList.setTotal(total);

        return messageBoardPageList;
    }

}
