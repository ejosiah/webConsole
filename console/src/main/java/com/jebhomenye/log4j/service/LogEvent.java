package com.jebhomenye.log4j.service;

import lombok.Data;

@Data
public class LogEvent {
	private final Long time;
	private final String message;
}
