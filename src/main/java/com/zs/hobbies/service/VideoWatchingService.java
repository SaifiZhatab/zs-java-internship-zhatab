package com.zs.hobbies.service;

import com.zs.hobbies.dto.VideoWatching;


/**
 * This is a remote class of Video Watching.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface VideoWatchingService extends HobbyService{
    void insert(VideoWatching videoWatching);
}
