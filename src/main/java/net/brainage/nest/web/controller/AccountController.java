/*
 * (#) net.brainage.nest.web.controller.AccountController.java
 * Created on 2016-05-09
 *
 * Copyright 2015 brainage.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.brainage.nest.web.controller;

import net.brainage.nest.data.model.User;
import net.brainage.nest.service.UserService;
import net.brainage.nest.web.form.SignupForm;
import net.brainage.nest.web.util.HttpRequestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Controller
@RequestMapping(path = {"/account"})
public class AccountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/signup/"}, method = RequestMethod.GET)
    public String signup(Model model) {
        return "account/signup";
    }

    @RequestMapping(path = {"/signup/"}, method = RequestMethod.POST)
    public String signup(SignupForm signupForm, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(signupForm);
            return signup(model);
        }

        /* convert dto to entity */
        User user = modelMapper.map(signupForm, User.class);
        user.setLang(HttpRequestUtils.getLanguage(request));
        userService.create(user);

        return "redirect:/account/signin/?created&next=/";
    }

}
