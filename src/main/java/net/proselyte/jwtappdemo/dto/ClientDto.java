package net.proselyte.jwtappdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.proselyte.jwtappdemo.model.Client;

import java.util.ArrayList;
import java.util.List;

import java.util.*;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {
    private Long id;
    private String Package;
    private String adressRecipient;
    private String adressSender;
    private String description;
    private String phone;

    public Client toClient(){
        Client client = new Client();
        client.setId(id);
        client.setPackage(Package);
        client.setAdressRecipient(adressRecipient);
        client.setAdressSender(adressSender);
        client.setDescription(description);
        client.setPhone(phone);
        return client;
    }

    public static ClientDto fromClient(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setPackage(client.getPackage());
        clientDto.setAdressRecipient(client.getAdressRecipient());
        clientDto.setAdressSender(client.getAdressSender());
        clientDto.setDescription(client.getDescription());
        clientDto.setPhone(client.getPhone());
        return clientDto;
    }

    public static List<ClientDto> fromClients(List<Client> clients) {

        ArrayList<ClientDto> cldto = new ArrayList<>();
        for (Client client: clients) {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setPackage(client.getPackage());
            clientDto.setAdressRecipient(client.getAdressRecipient());
            clientDto.setAdressSender(client.getAdressSender());
            clientDto.setDescription(client.getDescription());
            clientDto.setPhone(client.getPhone());
            cldto.add(clientDto);
        }
        return cldto;
    }
}
