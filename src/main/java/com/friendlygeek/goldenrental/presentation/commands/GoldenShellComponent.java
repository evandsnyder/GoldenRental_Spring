package com.friendlygeek.goldenrental.presentation.commands;

import org.springframework.shell.component.StringInput;
import org.springframework.shell.standard.AbstractShellComponent;

public class GoldenShellComponent extends AbstractShellComponent {
    protected String getInputManually(String fieldName) {
        return getInputManually(fieldName, false);
    }
    protected String getInputManually(String fieldName, boolean hidden) {
        StringInput component = new StringInput(getTerminal(), fieldName, null);
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        if (hidden) {
            component.setMaskCharater('*');
        }
        StringInput.StringInputContext context = component.run(StringInput.StringInputContext.empty());
        return context.getResultValue();
    }
}
