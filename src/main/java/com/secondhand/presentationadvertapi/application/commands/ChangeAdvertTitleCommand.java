package com.secondhand.presentationadvertapi.application.commands;

import com.secondhand.presentationadvertapi.application.bus.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeAdvertTitleCommand  implements Command {

    private Long id;

    private String title;

    public ChangeAdvertTitleCommand(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
