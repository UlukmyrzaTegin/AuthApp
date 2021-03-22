package sample.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * TheSusanin
 * 3/22/2021
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return null;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return null;
    }
}
