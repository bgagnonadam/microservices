package com.bgagnonadam.telephony.ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.bgagnonadam.telephony.ws.api.calllog.CallLogResource;
import com.bgagnonadam.telephony.ws.api.calllog.CallLogResourceImpl;
import com.bgagnonadam.telephony.ws.api.contact.ContactResource;
import com.bgagnonadam.telephony.ws.api.contact.ContactResourceImpl;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogAssembler;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogClient;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogService;
import com.bgagnonadam.telephony.ws.domain.contact.ContactAssembler;
import com.bgagnonadam.telephony.ws.domain.contact.ContactRepository;
import com.bgagnonadam.telephony.ws.domain.contact.ContactService;
import com.bgagnonadam.telephony.ws.http.CORSResponseFilter;
import com.bgagnonadam.telephony.ws.infrastructure.calllog.CallLogRestClient;
import com.bgagnonadam.telephony.ws.infrastructure.contact.ContactRepositoryInMemory;

/**
 * RESTApi setup without using DI or spring
 */
@SuppressWarnings("all")
public class TelephonyWsMain {
  private static final int HTTP_PORT = 8080;
public static boolean isDev = true; // Would be a JVM argument or in a .property file

  public static void main(String[] args)
          throws Exception {

    // Setup resources (API)
    ContactResource contactResource = createContactResource();
    CallLogResource callLogResource = createCallLogResource();

    // Setup API context (JERSEY + JETTY)
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");
    ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        HashSet<Object> resources = new HashSet<>();
        // Add resources to context
        resources.add(contactResource);
        resources.add(callLogResource);
        return resources;
      }
    });
    resourceConfig.register(CORSResponseFilter.class);

    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    // Setup static file context (WEBAPP)
    WebAppContext webapp = new WebAppContext();
    webapp.setResourceBase("src/main/webapp");
    webapp.setContextPath("/");

    // Setup http server
    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] { context, webapp });
    Server server = new Server(HTTP_PORT);
    server.setHandler(contexts);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

  private static ContactResource createContactResource() {
    // Setup resources' dependencies (DOMAIN + INFRASTRUCTURE)
    ContactRepository contactRepository = new ContactRepositoryInMemory();

    ContactAssembler contactAssembler = new ContactAssembler();
    ContactService contactService = new ContactService(contactRepository, contactAssembler);

    return new ContactResourceImpl(contactService);
  }

  private static CallLogResource createCallLogResource() {
    // Setup resources' dependencies (DOMAIN + INFRASTRUCTURE)
    CallLogClient callLogClient = new CallLogRestClient();
    
    CallLogAssembler callLogAssembler = new CallLogAssembler();
    CallLogService callLogService = new CallLogService(callLogClient, callLogAssembler);

    return new CallLogResourceImpl(callLogService);
  }
}
