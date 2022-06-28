package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Ab;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.catologmodel.Driver;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.catologmodel.Mark;
import ru.job4j.cars.model.catologmodel.Photo;
import ru.job4j.cars.repository.repcatalog.ColorService;
import ru.job4j.cars.service.AbService;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.UserService;
import ru.job4j.cars.service.servcatalog.*;

import java.io.IOException;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.5. Контрольные вопросы
 * 2. Тестовое задание. Hibernate. [#330581]
 * AbController контроллер отображение видов объявлений.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.06.2022
 */
@Controller
public class AbController {
    private final AbService abService;
    private final CarService carService;
    private final UserService userService;
    private final MarkService markService;
    private final ModelService modelService;
    private final YearService yearService;
    private final BodyService bodyService;
    private final EngineService engineService;
    private final CategoryService categoryService;
    private final TransmissionService transmissionService;
    private final ColorService colorService;

    public AbController(AbService abService, CarService carService, UserService userService,
                        MarkService markService, ModelService modelService, YearService yearService,
                        BodyService bodyService, EngineService engineService, CategoryService categoryService,
                        TransmissionService transmissionService, ColorService colorService) {
        this.abService = abService;
        this.carService = carService;
        this.userService = userService;
        this.markService = markService;
        this.modelService = modelService;
        this.yearService = yearService;
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.categoryService = categoryService;
        this.transmissionService = transmissionService;
        this.colorService = colorService;
    }

    /**
     * Отображение всех объявлений.
     *
     * @param model Model
     * @return index.html
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("abAll", abService.findAllAb());
        return "ab/index";
    }

    /**
     * Метод пост сохраняет новое объявление.
     *
     * @param car   Car
     * @param file0 MultipartFile
     * @param file1 MultipartFile
     * @param file2 MultipartFile
     * @return String
     * @throws IOException Exception.
     */
    @PostMapping("/createAb")
    public String create(@ModelAttribute Car car,
                         @RequestParam("file0") MultipartFile file0,
                         @RequestParam("file1") MultipartFile file1,
                         @RequestParam("file2") MultipartFile file2) throws IOException {
        User user = userService.findByUserId(1).get();
        car.addPhoto(Photo.of(file0.getBytes()));
        car.addPhoto(Photo.of(file1.getBytes()));
        car.addPhoto(Photo.of(file2.getBytes()));
        car.addDriver(Driver.of(user.getName(), user.getEmail()));
        carService.create(car);
        Car newCar = carService.findByIdCar(car.getId());
        abService.create(Ab.of(newCar, user));
        return "redirect:/";
    }

    /**
     * Вид отображения деталей объявления
     *
     * @param model
     * @param abId
     * @return
     */
    @GetMapping("/detailAb/{abId}")
    public String detailAb(Model model, @PathVariable("abId") int abId) {
        Ab ab = abService.findByIdAb(abId).get();
        model.addAttribute("ab", ab);
        return "ab/detailAb";
    }

    @GetMapping("/editAb")
    public String editAb(Model model, @RequestParam("id") int idAb) {
        Ab ab = abService.findByIdAb(idAb).get();
        Mark mark = ab.getCar().getModel().getMark();
        System.out.println(ab);
        model.addAttribute("car", new Car());
        model.addAttribute("mark", markService.findByIdMark(mark.getId()));
        model.addAttribute("models", modelService.findAllModelByMarkId(mark.getId()));
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("years", yearService.findAllYear());
        model.addAttribute("bodies", bodyService.findAllBody());
        model.addAttribute("engines", engineService.findAllEngine());
        model.addAttribute("transmissions", transmissionService.findAllTransmission());
        model.addAttribute("colors", colorService.findAllColor());
        model.addAttribute("ab", ab);
        return "ab/editAb";
    }
}
