package com.yourcompany.personalcrm;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.personalcrm.config.View;
import com.yourcompany.personalcrm.contacts.listcontacts.ContactSummary;
import com.yourcompany.personalcrm.contacts.listcontacts.ListContactsUseCase;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class HomeController
{
    @Hidden
    @GetMapping
    @View("index")
    public List<ContactSummary> homeData()
    {
        return listContactsUseCase.execute();
    }

    private final ListContactsUseCase listContactsUseCase;
}
