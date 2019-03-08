/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;
import com.accessObjects.*;
import java.util.ArrayList;
/**
 *
 * @author Abhishek Abhinav
 */
public interface DBCommentsINTF {
    ArrayList<Comment> getCommentsForDevice(String deviceID);
    boolean addComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(Comment comment);
}
