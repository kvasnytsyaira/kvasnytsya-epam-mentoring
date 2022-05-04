module jmp.cloud.service.impl{
        requires transitive jmp.service.api;
        requires jmp.dto;
        exports serviceImpl;
        provides service.MyService with serviceImpl.ServiceImpl;
        }
