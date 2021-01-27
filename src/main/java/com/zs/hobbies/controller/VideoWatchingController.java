package com.zs.hobbies.controller;


import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.service.VideoWatchingService;
import com.zs.hobbies.service.VideoWatchingServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class VideoWatchingController {
    private Person person;
    private Timing timing;
    private Logger logger;

    private VideoWatchingService videoWatchingService;

    private VideoWatchingController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        videoWatchingService = new VideoWatchingServiceImpl(con,lru);
        logger = Logger.getLogger(Application.class.getName());
    }
}
