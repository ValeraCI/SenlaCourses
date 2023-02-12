package senla.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{Application.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{Application.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
