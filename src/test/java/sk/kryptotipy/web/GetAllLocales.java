package sk.kryptotipy.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class GetAllLocales {

    public static void main(String[] args) {

        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            System.out.println("Country Code = " + obj.getCountry()
                    + ", Country Name = " + obj.getDisplayCountry());
        }
        Locale l = new Locale("", "US");
        Locale ll = new Locale("", "us");
        System.out.println("l=" + l.toString());
        System.out.println("ll=" + ll.toString());
    }


}
