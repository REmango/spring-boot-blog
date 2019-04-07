package com.remango.blog.controller;

import com.remango.blog.entity.MessageBoard;
import com.remango.blog.entity.PageList;
import com.remango.blog.service.MessageBoardService;
import com.remango.blog.vo.ErrorCode;
import com.remango.blog.vo.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by li on 2019/3/27.
 */
@Slf4j
@RestController
public class MessageBoardController {
    @Autowired
    private MessageBoardService messageBoardService;
    @ResponseBody
    @GetMapping("/getMessageBoard")
    public ResultBean getMessageBoardController (int offset, int limit) {
        PageList<MessageBoard> messageBoardPageList = messageBoardService.queryMessageList(offset, limit);
        return new ResultBean(messageBoardPageList);

    }

    @ResponseBody
    @PostMapping("/saveMessageBoard")
    public ResultBean saveMessageBoardController (HttpServletRequest request, MessageBoard messageBoard) throws IOException {
        log.debug(messageBoard.getMessage());
        log.debug(messageBoard.getNickname());
        log.warn(messageBoard.getNickname());
        request.setCharacterEncoding("utf-8");
        messageBoardService.saveMessage(messageBoard);
        return new ResultBean(ErrorCode.OK);

    }
}
