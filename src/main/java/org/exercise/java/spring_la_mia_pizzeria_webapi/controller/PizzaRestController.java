package org.exercise.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.List;
import java.util.Optional;

import org.exercise.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.exercise.java.spring_la_mia_pizzeria_webapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    // index
    @GetMapping
    public List<Pizza> index() {
        return pizzaService.findAll();
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable("id") Integer id) {
        Optional<Pizza> pizzaOptional = pizzaService.findById(id);
        if (pizzaOptional.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else {
            return new ResponseEntity<Pizza>(pizzaOptional.get(), HttpStatusCode.valueOf(200)); // 200 OK
        }
    }

    // create
    @PostMapping
    public ResponseEntity<Pizza> create(@Valid @RequestBody Pizza pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(400)); // 400 Bad Request
        } else {
            return new ResponseEntity<Pizza>(pizzaService.store(pizza), HttpStatusCode.valueOf(201)); // 201 Created
        }
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable("id") Integer id, @Valid @RequestBody Pizza pizza,
            BindingResult bindingResult) {
        if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(400)); // 400 Bad Request
        } else {
            pizza.setId(id);
            return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatusCode.valueOf(200)); // 200 OK
        }
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable("id") Integer id) {
        if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(404)); // 404 Not Found
        } else {
            pizzaService.deleteById(id);
            return new ResponseEntity<Pizza>(HttpStatusCode.valueOf(200)); // 200 OK
        }
    }
    // post and put request not working correctly
    // "status": 415
    // "error": 415 Unsupported Media Type
    // "trace": "org.springframework.web.HttpMediaTypeNotSupportedException:
    // Content-Type 'application/json;charset=UTF-8' is not supported
    // org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:236)
    // org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:176)
    // org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:150)
    // org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:122)
    // org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:227)
    // org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:181)
    // org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
    // org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:991)
    // org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:896)
    // org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
    // org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
    // org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
    // org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
    // org.springframework.web.servlet.FrameworkServlet.doPut(FrameworkServlet.java:925)
    // jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
    // org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
    // jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    // org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)
    // org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    // org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
    // org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
    // org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    // org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
    // org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
    // org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
    // org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    // org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
    // org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
    // org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
    // org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    // org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
    // org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
    // org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
    // org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    // org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    // org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    // org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)
    // org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:116)
    // org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    // org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    // org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
    // org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:398)
    // org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    // org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903)
    // org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1776)
    // org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
    // org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:975)
    // org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:493)
    // org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
    // java.base/java.lang.Thread.run(Unknown Source)",
    // "message": "Content-Type 'application/json;charset=UTF-8' is not supported.",
    // "path": "/api/pizzas/15"
}
