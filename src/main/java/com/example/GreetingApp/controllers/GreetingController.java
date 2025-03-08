package com.example.GreetingApp.controllers;
import com.example.GreetingApp.dto.MessageDTO;
import com.example.GreetingApp.interfaces.IGreetingInterface;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("greetings")
public class GreetingController {

    //Replacing constructor injection with interface-(UC-Adding-Interfaces_Autowired)

    IGreetingInterface iGreetingInterface;

    @GetMapping("/get")
    public String getGreetings(){
        return "{\"message\": \"Hello from GET Request!\"}";
    }

    @PostMapping("/post")
    public String postGreetings(@RequestBody MessageDTO message){
        return "{\""+message.getMessage()+": \"Hello from POST Request!\"}";
    }

    @PutMapping("/put/{message}")
    public String putGreetings(@PathVariable String message){
        return "{\""+message+": \"Hello from PUT Request!\"}";
    }

    //UC2
    @GetMapping("/service")
    public String serviceGreetings(){
        return iGreetingInterface .getGreetings();
    }

    @GetMapping("/query")
    public String query(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName){

        if(firstName != null && lastName != null)
            return "Hello "+firstName+" "+lastName+" Welcome to Bridgelabz";
        else if(firstName != null)
            return "Hello "+firstName+" Welcome to Bridgelabz";
        else if(lastName != null)
            return "Hello "+lastName+" Welcome to Bridgelabz";
        else
            return "Hello, Welcome to Bridgelabz";
    }

    @PostMapping("/save")
    public MessageDTO save(@RequestBody MessageDTO message){
        return iGreetingInterface .saveGreetings(message);
    }


    @GetMapping("/find/{id}")
    public MessageDTO findById(@PathVariable Long id){


        return iGreetingInterface .findById(id);

    }

    @GetMapping("/listAll")
    public List<MessageDTO> listAll(){

        return iGreetingInterface .listAll();
    }

    @PostMapping("/edit/{id}")
    public MessageDTO editById(@RequestBody MessageDTO message, @PathVariable Long id){

        return iGreetingInterface .editById(message, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return iGreetingInterface .delete(id);
    }

}