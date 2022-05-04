module jmp.cloud.bank.impl{
        requires transitive jmp.bank.api;
        requires jmp.dto;
        exports layerImpl;
        provides layer.Bank with layerImpl.BankImpl;
        }

