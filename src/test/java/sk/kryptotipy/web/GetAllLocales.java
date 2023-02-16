package sk.kryptotipy.web;

import java.util.Locale;

public class GetAllLocales {

    public static void main(String[] args) {

        var locales = Locale.getISOCountries();
        for (var countryCode : locales) {
            var obj = new Locale("", countryCode);
            System.out.println("Country Code = " + obj.getCountry()
                    + ", Country Name = " + obj.getDisplayCountry());
        }
        var l = new Locale("", "US");
        var ll = new Locale("", "us");
        System.out.println("l=" + l.toString());
        System.out.println("ll=" + ll.toString());
    }
}
