module application{
    requires jmp.dto;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    uses layer.Bank;
    uses service.MyService;
}
