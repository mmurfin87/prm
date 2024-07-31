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
import com.yourcompany.personalcrm.fieldtypes.addcustomfieldtype.AddCustomFieldTypeRepository;
import com.yourcompany.personalcrm.fieldtypes.createinstance.CreateInstanceRepository;
import com.yourcompany.personalcrm.fieldtypes.deletecustomfieldtype.DeleteCustomFieldTypeRepository;
import com.yourcompany.personalcrm.fieldtypes.listfieldtypes.ListFieldTypesRepository;
import com.yourcompany.personalcrm.fieldtypes.listinstances.ListInstancesRepository;
import com.yourcompany.personalcrm.interactions.addinteraction.AddInteractionRepository;
import com.yourcompany.personalcrm.interactions.deleteinteraction.DeleteInteractionRepository;
import com.yourcompany.personalcrm.interactiontypes.addinteractiontype.AddInteractionTypeRepository;
import com.yourcompany.personalcrm.interactiontypes.deleteinteractiontype.DeleteInteractionTypeRepository;
import com.yourcompany.personalcrm.interactiontypes.listinteractiontypes.ListInteractionTypesRepository;
import com.yourcompany.personalcrm.login.UserDetailsServiceRepository;
import com.yourcompany.personalcrm.relationships.addrelationship.AddRelationshipRepository;
import com.yourcompany.personalcrm.relationships.deleterelationship.DeleteRelationshipRepository;
import com.yourcompany.personalcrm.relationships.inspectcontactrelationships.InspectContactRelationshipRepository;
import com.yourcompany.personalcrm.relationships.listrelationships.ListRelationshipsRepository;
import com.yourcompany.personalcrm.relationshiptypes.addrelationshiptype.AddRelationshipTypeRepository;
import com.yourcompany.personalcrm.relationshiptypes.deleterelationshiptype.DeleteRelationshipTypeRepository;
import com.yourcompany.personalcrm.relationshiptypes.listrelationshiptypes.ListRelationshipTypesRepository;
import com.yourcompany.personalcrm.signup.SignUpRepository;

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

    @Bean
    public DeleteInteractionRepository deleteInteractionRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(DeleteInteractionRepository.class);
    }

    @Bean
    public ListRelationshipTypesRepository listRelationshipTypesRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(ListRelationshipTypesRepository.class);
    }

    @Bean
    public AddRelationshipTypeRepository addRelationshipTypeRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(AddRelationshipTypeRepository.class);
    }

    @Bean
    public DeleteRelationshipTypeRepository deleteRelationshipTypeRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(DeleteRelationshipTypeRepository.class);
    }

    @Bean
    public AddRelationshipRepository addRelationshipRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(AddRelationshipRepository.class);
    }

    @Bean
    public InspectContactRelationshipRepository inspectContactRelationshipRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(InspectContactRelationshipRepository.class);
    }

    @Bean
    public DeleteRelationshipRepository deleteRelationshipRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(DeleteRelationshipRepository.class);
    }

    @Bean
    public ListRelationshipsRepository listRelationshipsRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(ListRelationshipsRepository.class);
    }

    @Bean
    public AddInteractionTypeRepository addInteractionTypeRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(AddInteractionTypeRepository.class);
    }

    @Bean
    public ListInteractionTypesRepository listInteractionTypesRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(ListInteractionTypesRepository.class);
    }

    @Bean
    public DeleteInteractionTypeRepository deleteInteractionTypeRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(DeleteInteractionTypeRepository.class);
    }

    @Bean
    public AddCustomFieldTypeRepository addCustomFieldTypeRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(AddCustomFieldTypeRepository.class);
    }

    @Bean
    public DeleteCustomFieldTypeRepository deleteCustomFieldTypeRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(DeleteCustomFieldTypeRepository.class);
    }

    @Bean
    public ListFieldTypesRepository listFieldTypesRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(ListFieldTypesRepository.class);
    }

    @Bean
    public CreateInstanceRepository createInstanceRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(CreateInstanceRepository.class);
    }

    @Bean
    public ListInstancesRepository listInstancesRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(ListInstancesRepository.class);
    }

    @Bean
    public UserDetailsServiceRepository userDetailsServiceRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(UserDetailsServiceRepository.class);
    }

    @Bean
    public SignUpRepository SignUpRepository(@NonNull final Jdbi jdbi)
    {
        return jdbi.onDemand(SignUpRepository.class);
    }
}