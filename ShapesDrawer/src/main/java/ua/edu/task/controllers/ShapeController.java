package ua.edu.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.task.dao.CrudDao;
import ua.edu.task.models.Position;
import ua.edu.task.models.Shape;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ShapeController {
    @Autowired
    private CrudDao crudDao;


    @RequestMapping(path = "/shapes", method = RequestMethod.GET)
    public ModelAndView startPage(){
        ModelAndView modelAndView = new ModelAndView("start");
        return modelAndView;
    }

    @RequestMapping(path = "/drawShape", method = RequestMethod.GET)
    public ModelAndView showAll(){
        List<Shape> shapes = crudDao.findAll();
        ModelAndView modelAndView = new ModelAndView("choseShape");
        modelAndView.addObject("shapesList", shapes);
        return modelAndView;
    }


    @RequestMapping(path = "/drawShape", method = RequestMethod.POST)
    public ModelAndView drawShape(@ModelAttribute Shape attributeShape){ // переробити з @RequestParam
        ModelAndView modelAndView = new ModelAndView("drawShape");
        Shape shape =(Shape) crudDao.find(attributeShape.getName());
        modelAndView.addObject("shape", shape);
        return modelAndView;
    }

    @RequestMapping(path = "/addShape", method = RequestMethod.GET)
    public ModelAndView addShape(){
        ModelAndView modelAndView = new ModelAndView("addShape");
        return modelAndView;
    }

    @RequestMapping(path = "/addShape", method = RequestMethod.POST)
    public ModelAndView addShape(@RequestParam String name, @RequestParam String position){

        ModelAndView modelAndView = new ModelAndView("addShape");
        String[] s = position.split(" ");
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < s.length - 1; i+=2) {
            Integer x = Integer.parseInt(s[i]);
            Integer y = Integer.parseInt(s[i+1]);
            Position position1 = new Position(x,y);
            positions.add(position1);
        }

        Shape shape = new Shape(name,positions);
        crudDao.save(shape);
        return modelAndView;
    }

    @RequestMapping(path = "/deleteShape", method = RequestMethod.GET)
    public ModelAndView deleteShape(){
        List<Shape> shapes = crudDao.findAll();
        ModelAndView modelAndView = new ModelAndView("deleteShape");
        modelAndView.addObject("shapesList", shapes);
        return modelAndView;
    }

    @RequestMapping(path = "/deleteShape", method = RequestMethod.POST)
    public ModelAndView deleteShape(@RequestParam String name){
        crudDao.delete(name);
        List<Shape> shapes = crudDao.findAll();
        ModelAndView modelAndView = new ModelAndView("deleteShape");
        modelAndView.addObject("shapesList", shapes);
        return modelAndView;
    }




}
