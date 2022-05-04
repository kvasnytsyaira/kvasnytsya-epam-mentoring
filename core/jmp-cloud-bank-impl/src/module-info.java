module jmp-cloud-bank-impl{
        requires transitive jmp-bank-api.layer;
        requires jmp-dto;
        exports jmp-cloud-bank-api.layerImpl;
        provides Bank with BankImpl;
        }

