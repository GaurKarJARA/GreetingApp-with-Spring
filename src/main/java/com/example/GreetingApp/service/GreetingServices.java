package com.example.GreetingApp.service;
import com.example.GreetingApp.entities.MessageEntity;
import com.example.GreetingApp.interfaces.IGreetingInterface;
import com.example.GreetingApp.repository.GreetingRepository;
import com.example.GreetingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.GreetingApp.dto.MessageDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GreetingServices implements IGreetingInterface {
    String message;

    @Autowired
    GreetingRepository greetingRepository;


    public void GreetingService() {

        message = "Hello World!";
    }


    public String getGreetings(){
        return this.message;
    }

    public MessageDTO saveGreetings(MessageDTO message){

        MessageEntity me = new MessageEntity(message.getMessage());

        greetingRepository.save(me);

        MessageDTO dto = new MessageDTO(me.getMessage());

        dto.setId(me.getId());

        return dto;
    }

    public MessageDTO findById(Long id){

        MessageEntity m1 = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("No messages found with given id"));

        MessageDTO m2 = new MessageDTO(m1.getMessage());
        m2.setId(m1.getId());

        return m2;

    }

    public List<MessageDTO> listAll(){

        List<MessageDTO> list = greetingRepository.findAll().stream().map(entity -> {
            MessageDTO m = new MessageDTO(entity.getMessage());
            m.setId(entity.getId());
            return m;
        }).collect(Collectors.toList());

        return list;
    }

    public MessageDTO editById(MessageDTO message, Long id){

        MessageEntity m = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("No Message was found with given id"));

        m.setMessage(message.getMessage());

        greetingRepository.save(m);

        MessageDTO m2 = new MessageDTO(m.getMessage());
        m2.setId(m.getId());

        return m2;
    }

    public String delete(Long id){

        MessageEntity m = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find message with given id"));

        greetingRepository.delete(m);

        return "Message Deleted";

    }

}
