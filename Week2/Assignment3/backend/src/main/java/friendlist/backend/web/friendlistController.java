package friendlist.backend.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import friendlist.backend.domain.Friend;


@Controller
public class friendlistController {

    private List<Friend> friends = new ArrayList<>();

    @GetMapping("/hello")
    public String getFriends(@RequestParam String firstName, @RequestParam String lastName, Model model) {
        if(firstName != null && lastName != null) {
            Friend newFriend = new Friend(firstName, lastName);
            friends.add(newFriend);
        }

        model.addAttribute("message", "My Friends");
        model.addAttribute("friends", friends);

        return "friendslist";
    }

    @GetMapping("/add")
    public String showAddFriendForm() {
        return "addfriend";
    }

    @PostMapping("/add")
    public String addFriend(@RequestParam String firstName, @RequestParam String lastName, Model model) {
        Friend newFriend = new Friend(firstName, lastName);
        friends.add(newFriend);

        model.addAttribute("message", "Welcome to the Friend List");
        model.addAttribute("friends", friends);
        return "friendslist"; 
    }
}
