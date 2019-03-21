package com.controller;

import static com.accessObjects.Globals.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.weblogics.More4Servlets;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhishek Abhinav
 */
public class Filter extends HttpServlet {

    public static TreeMap<String, ArrayList<String>> final_filterData;
    public static ArrayList<String> filter_DeviceIDs;
    public static TreeMap<String, ArrayList<String>> traversedData;
    public TreeMap<String, ArrayList<String>> traversedPacks;
    public TreeMap<String, ArrayList<String>> toTraverse;
    int loaded;

    @Override
    public void init() {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String reqStr = request.getParameter("filterstring");
            if (reqStr.equalsIgnoreCase("reset")) {
                final_filterData = new TreeMap<>();
                traversedData = new TreeMap<>();
                traversedPacks = new TreeMap<>();
                toTraverse = new TreeMap<>();
                filter_DeviceIDs = new ArrayList<>();
                loaded = 0;
                out.println("Loading... 0%");
            } else if (reqStr.equalsIgnoreCase("get result")) {
                out.println(filter_DeviceIDs.size());
            } else {
                loaded++;
                String filterstring = request.getParameter("filterstring");
                String filterData[] = filterstring.split(",");
                int min = 9999, temp;
                String reqFilter = "", reqValue = "";

                for (int i = 0; i < filterData.length; i++) {
                    String filterType = filterData[i].split(":")[0];
                    String filterValue = filterData[i].split(":")[1];
                    if (filterType.equalsIgnoreCase("brand") || filterType.equalsIgnoreCase("os")) {
                        temp = Integer.parseInt(toTreeMap(FILTER_SIZE.get(filterType)).get(filterValue.toLowerCase()));

                    } else {
                        temp = Integer.parseInt(More4Servlets.getFilterPacks(filterType, filterValue).get(0));
                    }
                    if (temp < min) {
                        min = temp;
                        reqFilter = filterType;
                        reqValue = filterValue.toLowerCase();
                    }
                }

                if (!toTraverse.containsKey(reqFilter)) {

                    ArrayList<String> reqURLs = getTraverse_URLs(reqFilter, reqValue, out);
                    toTraverse.put(reqFilter, reqURLs);
                    traversedPacks.put(reqFilter, new ArrayList<String>());
                    //out.println(new Gson().toJson(reqURLs));
                }
                if (traversedPacks.get(reqFilter).size() != toTraverse.get(reqFilter).size()) {
                    //out.println("inside if");
                    int traverse_index = traversedPacks.get(reqFilter).size();
                    //out.println("conv");
                    //out.println("url");
                    URL url = new URL(toTraverse.get(reqFilter).get(traverse_index));
                    //out.println("url end");
                    JsonReader jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
                    boolean exception = false;
                    try {
                        //out.println("try");
                        jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
                        Map<String, ArrayList<String>> vector = new HashMap<>();
                        jsonReader.beginObject();
                        Gson gson = new GsonBuilder().create();
                        while (jsonReader.hasNext()) {
                            String model = jsonReader.nextName();
                            ArrayList<String> devarr = gson.fromJson(jsonReader.nextString(), ArrayList.class);
                            String brand = devarr.get(0);
                            traversedData.put(model, devarr);
                            boolean satisfy = false;
                            for (int i = 0; i < filterData.length; i++) {
                                String filterType = filterData[i].split(":")[0];
                                String filterValue = filterData[i].split(":")[1];
                                if (filterType.equalsIgnoreCase("os")) {
                                    if (devarr.get(VECTOR_INDEX.indexOf(filterValue.toLowerCase())).equals("100")) {
                                        satisfy = true;
                                    } else {
                                        satisfy = false;
                                        break;
                                    }
                                } else if (filterType.equalsIgnoreCase("brand")) {
                                    if (devarr.get(0).equals(filterValue.toLowerCase())) {
                                        satisfy = true;
                                    } else {
                                        satisfy = false;
                                        break;
                                    }
                                } else if (filterType.equalsIgnoreCase("internal") || filterType.equalsIgnoreCase("external")
                                        || filterType.equalsIgnoreCase("selfiecam") || filterType.equalsIgnoreCase("maincam")
                                        || filterType.equalsIgnoreCase("battery") || filterType.equalsIgnoreCase("ram")) {
                                    
                                    double from = Double.parseDouble(filterValue.split("to")[0]);
                                    double to = 0;
                                    if (filterValue.split("to")[1].equalsIgnoreCase("inf")) {
                                        to = 99999;
                                    } else {
                                        to = Double.parseDouble(filterValue.split("to")[1]);
                                    }
                                    
                                    double data;
                                    if (filterType.equalsIgnoreCase("selfiecam") || filterType.equalsIgnoreCase("maincam")) {
                                        data = Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf(filterType.toLowerCase()))) / 30.0;
                                    } else if (filterType.equalsIgnoreCase("battery")) {
                                        data = Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf(filterType.toLowerCase()))) / 50;
                                    } else {
                                        data = Double.parseDouble(devarr.get(VECTOR_INDEX.indexOf(filterType.toLowerCase())));
                                    }
                                    if((data>from || (data==0 && data>=from)) && data<=to){
                                        satisfy = true;
                                    } else {
                                        satisfy = false;
                                        break;
                                    }
                                }

                            }
                            if(satisfy)
                                filter_DeviceIDs.add(brand + "%" + encode4Firebase(model));

                        }
                        jsonReader.endObject();

                        jsonReader.close();
                        traversedPacks.get(reqFilter).add(toTraverse.get(reqFilter).get(traverse_index));
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
                    int done = (int) Math.floor(100.0 * traversedPacks.get(reqFilter).size() / toTraverse.get(reqFilter).size());
                    out.println("Loading... " + done + "%");
                } else {
                    out.println("Loading... 100%");
                }
            }
        }
    }

    protected static ArrayList<String> getTraverse_URLs(String reqFilter, String reqValue, PrintWriter out) throws MalformedURLException {
        ArrayList<String> reqURLs = new ArrayList<>();
        if (reqFilter.equals("brand")) {
            String pack = BRAND_MAPPING.get(reqValue);
            String urlstr = "https://device-pics.firebaseapp.com/filters/" + reqFilter + "/devicevector-" + reqFilter + "-" + pack + ".json";
            reqURLs.add(urlstr);
        } else if (reqFilter.equals("internal") || reqFilter.equals("external") || reqFilter.equals("ram")
                || reqFilter.equals("selfiecam") || reqFilter.equals("maincam") || reqFilter.equals("battery")) {
            ArrayList<String> packs = More4Servlets.getFilterPacks(reqFilter, reqValue);
            for (int i = 1; i < packs.size(); i++) {
                String urlstr = "https://device-pics.firebaseapp.com/filters/" + reqFilter + "/devicevector-" + reqFilter + "-" + packs.get(i) + ".json";
                reqURLs.add(urlstr);
            }

        } else if (reqFilter.equals("os")) {

            if (reqValue.equals("android")) {

                for (int i = 0; i <= 11; i++) {
                    String pack = "pack-" + i;
                    String urlstr = "https://device-pics.firebaseapp.com/filters/" + reqFilter + "/devicevector-android-" + pack + ".json";
                    reqURLs.add(urlstr);
                }

            } else {
                String urlstr = "https://device-pics.firebaseapp.com/filters/" + reqFilter + "/devicevector-" + reqValue + ".json";
                reqURLs.add(urlstr);
            }
        }
        return reqURLs;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
