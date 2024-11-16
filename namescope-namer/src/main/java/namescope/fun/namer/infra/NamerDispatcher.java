package namescope.fun.namer.infra;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.enums.NamerEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class NamerDispatcher implements ApplicationContextAware {

    private static final Map<Integer, Nameable> NAMER_CONTEXT = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    @PostConstruct
    public void initNamerContext() {
        Map<String, Object> namers = applicationContext.getBeansWithAnnotation(Namer.class);
        for (NamerEnum namerEnum : NamerEnum.values()) {
            String namerType = namerEnum.getNamerType();
            Integer namerId = namerEnum.getNamerId();
            if (!namers.containsKey(namerType)) {
                log.warn("[namer context init] namer {} unregistered, namerId: {}", namerType, namerId);
                continue;
            }

            Nameable namer = (Nameable) namers.get(namerType);
            NAMER_CONTEXT.put(namerId, namer);
            log.info("[namer context init] namer {} registered, namerId: {}", namerType, namerId);
        }
    }

    public void process(NameContext nameContext) {
        Nameable namer = getInstance(nameContext.getNameParam().getNamerId());
        if (Objects.isNull(namer)) {
            log.error("[namer dispatch] namerId {} is not found", nameContext.getNameParam().getNamerId());
        }
        namer.name(nameContext);
    }

    public Nameable getInstance(Integer namerId) {
        return NAMER_CONTEXT.get(namerId);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
