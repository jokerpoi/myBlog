package com.poi.service;

import com.poi.entity.DataGrid;
import com.poi.entity.User;
import com.poi.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserJpa userJpa;

    public List<User> findAll(){
        return userJpa.findAll();
    }

    public User getUserById(int id){
        User user = null;
        Optional<User> userOpt = userJpa.findById(new Integer(id));
        if(!Optional.empty().equals(userOpt)){
            user = userOpt.get();
        }
        return user;
    }

    public User addUser(User user){
        User u = new User();
        if(null != user.getUsername() && !user.getUsername().isEmpty()){
            u.setUsername(user.getUsername());
        }
        if(null != user.getEmail() && !user.getEmail().isEmpty()){
            u.setEmail(user.getEmail());
        }
        if(null != user.getPassword() && !user.getPassword().isEmpty()){
            u.setPassword(user.getPassword());
        }
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        u.setCreated(timeNow);
        return userJpa.save(u);
    }

    public void updateUser(User u){
        User user = userJpa.getOne(u.getUid());
        u.setCreated(user.getCreated());
        userJpa.save(u);
    }

    public void removeUser(int id){
        userJpa.deleteById(id);
    }

    public DataGrid<User> findUserInPage(int limit,int offset){
        DataGrid<User> dataGrid = new DataGrid<User>();
        User config = new User();
        config.setPage(offset);
        config.setPageSize(limit);
        config.setSidx("modified");
        config.setSord("desc");

        Sort.Direction sortDirection = Sort.Direction.ASC.toString().equalsIgnoreCase(config.getSord())?Sort.Direction.ASC:Sort.Direction.DESC;
        Sort sort = new Sort(sortDirection,config.getSidx());
        PageRequest pageRequest = new PageRequest(config.getPage(),config.getPageSize(),sort);

        List<User> userPageList = userJpa.findAll(pageRequest).getContent();
        List<User> userAll = userJpa.findAll();

        dataGrid.setRows(userPageList);
        dataGrid.setTotal(userAll.size());

        return dataGrid;
    }
}
