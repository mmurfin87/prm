package com.yourcompany.personalcrm.config;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.yourcompany.personalcrm.contacts.addcontact.AddContactRepository;
import com.yourcompany.personalcrm.contacts.deletecontact.DeleteContactRepository;
import com.yourcompany.personalcrm.contacts.inspectcontactdetails.InspectContactDetailsRepository;
import com.yourcompany.personalcrm.contacts.listcontacts.ListContactsRepository;
import com.yourcompany.personalcrm.contacts.updatecontact.UpdateContactRepository;
import com.yourcompany.personalcrm.interactions.addinteraction.AddInteractionRepository;

import lombok.NonNull;

import javax.sql.DataSource;

@Configuration
public class JdbiConfiguration
{
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        //dataSource.setUrl("jdbc:h2:file:./data/personalcrm;MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
        dataSource.setUrl("jdbc:h2:file:./data/personalcrm;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public Jdbi jdbi(@NonNull final DataSource dataSource)
    {
        return Jdbi.create(dataSource)
            .installPlugin(new SqlObjectPlugin());
    }

    @Bean
    public AddContactRepository addContactRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(AddContactRepository.class);
    }

    @Bean
    public ListContactsRepository listContactsRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(ListContactsRepository.class);
    }

    @Bean
    public InspectContactDetailsRepository inspectContactDetailsRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(InspectContactDetailsRepository.class);
    }

    @Bean
    public UpdateContactRepository updateContactRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(UpdateContactRepository.class);
    }

    @Bean
    public DeleteContactRepository deleteContactRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(DeleteContactRepository.class);
    }

    @Bean
    public AddInteractionRepository addInteractionRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(AddInteractionRepository.class);
    }
}