package C1Basic.code.conditional.service.impl;

import C1Basic.code.conditional.service.ListService;


public class WindowsServiceImpl implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
