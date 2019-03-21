/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;

import static com.accessObjects.Globals.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abhishek Abhinav
 */
public class GetFeature implements GetFeatureINTF {

    Map<String, Map<String, ArrayList<String>>> devicevector_brand = new HashMap<>();

    @Override
    public String getProperty(String deviceID, String prop) {
        String brand = deviceID.split("%")[0];
        String model = deviceID.split("%")[1];
        String prop_val = "";
        String brandPack = BRAND_MAPPING.get(brand);
        boolean exception = false;
        JsonReader jsonReader = null;
        if (devicevector_brand.containsKey(brand)) {
            if (devicevector_brand.get(brand).containsKey(model)) {
                if (prop.equals("selfiecam") || prop.equals("maincam")) {
                    prop_val = Double.parseDouble(devicevector_brand.get(brand).get(model).get(VECTOR_INDEX.indexOf(prop))) / 30 + " MP";
                } else if (prop.equals("ram") || prop.equals("external") || prop.equals("internal")) {
                    double val = Double.parseDouble(devicevector_brand.get(brand).get(decodeNormal(model)).get(VECTOR_INDEX.indexOf(prop)));
                    if (val > 0 && val < 1) {
                        prop_val = val * 1024 + " MB";
                    } else if (val > 1) {
                        prop_val = val + " GB";
                    } else if (val == 0) {
                        prop_val = "";
                    }
                } else if (prop.equals("battery")) {
                    double val = Double.parseDouble(devicevector_brand.get(brand).get(model).get(VECTOR_INDEX.indexOf(prop))) * 50;
                    prop_val = val + " mAh";
                }
            }
        } else {

            try {
                URL url = new URL("https://device-pics.firebaseapp.com/filters/brand/devicevector-brand-" + brandPack + ".json");
                jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
                Map<String, ArrayList<String>> vector = new HashMap<>();
                jsonReader.beginObject();
                Gson gson = new GsonBuilder().create();
                Map<String, ArrayList<String>> devicevector = new HashMap<>();

                while (jsonReader.hasNext()) {
                    String modeliter = jsonReader.nextName();
                    ArrayList<String> devarr = gson.fromJson(jsonReader.nextString(), ArrayList.class);
                    String branditer = devarr.get(0);
                    devicevector.put(modeliter, devarr);

                    if (model.equalsIgnoreCase(modeliter)) {
                        if (prop.equals("selfiecam") || prop.equals("maincam")) {
                            prop_val = Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf(prop))) / 30 + " MP";
                        } else if (prop.equals("ram") || prop.equals("external") || prop.equals("internal")) {
                            double val = Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf(prop)));
                            if (val > 0 && val < 1) {
                                prop_val = val * 1024 + " MB";
                            } else if (val > 1) {
                                prop_val = val + " GB";
                            } else if (val == 0) {
                                prop_val = "";
                            }
                        } else if (prop.equals("battery")) {
                            double val = Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf(prop))) * 50;
                            prop_val = val + " mAh";
                        }
                    }
                }
                if (!jsonReader.hasNext()) {
                    jsonReader.endObject();
                }
                devicevector_brand.put(brand, devicevector);
                jsonReader.close();
            } catch (MalformedURLException ex) {
                exception = true;
            } catch (IOException ex) {
                exception = true;
            } finally {
                try {
                    if (!exception) {
                        jsonReader.close();
                    }
                } catch (IOException ex) {

                }
            }
        }
        return prop_val;
    }

    @Override
    public String getCardType(String deviceID) {
        String cardType = "";
        String brand = deviceID.split("%")[0];
        String model = deviceID.split("%")[1];
        if (devicevector_brand.containsKey(brand)) {
            if (Double.parseDouble(devicevector_brand.get(brand).get(model).get(VECTOR_INDEX.indexOf("microsd"))) == 100) {
                cardType = "microSD";
            } else if (Double.parseDouble(devicevector_brand.get(brand).get(model).get(VECTOR_INDEX.indexOf("minisd"))) == 100) {
                cardType = "miniSD";
            }
        } else {
            String brandPack = BRAND_MAPPING.get(brand);
            boolean exception = false;
            JsonReader jsonReader = null;
            try {
                URL url = new URL("https://device-pics.firebaseapp.com/filters/brand/devicevector-brand-" + brandPack + ".json");
                jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
                Map<String, ArrayList<String>> vector = new HashMap<>();
                jsonReader.beginObject();
                Gson gson = new GsonBuilder().create();
                Map<String, ArrayList<String>> devicevector = new HashMap<>();

                while (jsonReader.hasNext()) {
                    String modeliter = jsonReader.nextName();
                    ArrayList<String> devarr = gson.fromJson(jsonReader.nextString(), ArrayList.class);
                    String branditer = devarr.get(0);
                    devicevector.put(modeliter, devarr);

                    if (model.equalsIgnoreCase(modeliter)) {
                        if (Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf("microsd"))) == 100) {
                            cardType = "microSD";
                        } else if (Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf("minisd"))) == 100) {
                            cardType = "miniSD";
                        }
                    }
                }
                if (!jsonReader.hasNext()) {
                    jsonReader.endObject();
                }
                devicevector_brand.put(brand, devicevector);
                jsonReader.close();
            } catch (MalformedURLException ex) {
                exception = true;
            } catch (IOException ex) {
                exception = true;
            } finally {
                try {
                    if (!exception) {
                        jsonReader.close();
                    }
                } catch (IOException ex) {

                }
            }
        }
        return cardType;
    }

    @Override
    public String has4G(String deviceID) {
        String has4g = "No";
        String brand = deviceID.split("%")[0];
        String model = deviceID.split("%")[1];
        if (devicevector_brand.containsKey(brand)) {
            if (Double.parseDouble(devicevector_brand.get(brand).get(model).get(VECTOR_INDEX.indexOf("4g"))) == 100) {
                has4g = "Yes";
            }
        } else {
            String brandPack = BRAND_MAPPING.get(brand);
            boolean exception = false;
            JsonReader jsonReader = null;
            try {
                URL url = new URL("https://device-pics.firebaseapp.com/filters/brand/devicevector-brand-" + brandPack + ".json");
                jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
                Map<String, ArrayList<String>> vector = new HashMap<>();
                jsonReader.beginObject();
                Gson gson = new GsonBuilder().create();
                Map<String, ArrayList<String>> devicevector = new HashMap<>();

                while (jsonReader.hasNext()) {
                    String modeliter = jsonReader.nextName();
                    ArrayList<String> devarr = gson.fromJson(jsonReader.nextString(), ArrayList.class);
                    String branditer = devarr.get(0);
                    devicevector.put(modeliter, devarr);

                    if (model.equalsIgnoreCase(modeliter)) {
                        if (Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf("4g"))) == 100) {
                            has4g = "Yes";
                        }
                    }
                }
                if (!jsonReader.hasNext()) {
                    jsonReader.endObject();
                }
                devicevector_brand.put(brand, devicevector);
                jsonReader.close();
            } catch (MalformedURLException ex) {
                exception = true;
            } catch (IOException ex) {
                exception = true;
            } finally {
                try {
                    if (!exception) {
                        jsonReader.close();
                    }
                } catch (IOException ex) {

                }
            }
        }
        return has4g;
    }

}
