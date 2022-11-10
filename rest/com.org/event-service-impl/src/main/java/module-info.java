module event.service.impl {
    requires transitive event.service.api;
    requires event.service.dto;
    requires spring.context;
    exports serviceImpl;
    provides service.EventService with serviceImpl.EventServiceImpl;
}