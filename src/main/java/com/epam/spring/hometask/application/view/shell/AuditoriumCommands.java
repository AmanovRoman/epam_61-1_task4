package com.epam.spring.hometask.application.view.shell;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AuditoriumCommands {
    @Autowired
    AuditoriumService auditoriumService;

    @ShellMethod(
            value = "Show whole auditorium list",
            key = {"auds"}
    )
    public void showAuditories() {
        System.out.println("\nAUDITORIUMS:\n----------------------------------------");
        auditoriumService.getAllAuditoriums().forEach(System.out::println);
    }

    @ShellMethod(
            value = "Show auditorium by ID (id)",
            key = {"aud get"}
    )
    public Auditorium showAuditoryById(int id) {
        return this.auditoriumService.getAuditoriumById(id);
    }
}
