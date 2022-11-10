module event.service.rest {
    requires event.service.dto;
    requires event.service.impl;
    requires spring.web;
    requires spring.context;
    requires spring.beans;
    uses service.EventService;
}