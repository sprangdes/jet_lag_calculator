import requests
import json
from bs4 import BeautifulSoup

with open("./jet_lag_calculator/data.json", 'w', encoding="utf-8") as json_file:

    json_file.write('{\n\t"citys": [ \n')

    url = "https://fate.windada.com/cgi-bin/worldtime"

    response = requests.get(url)
    response.encoding = "utf-8"

    if response.status_code == 200:
        html_content = response.text
    else:
        print(f"Failed to retrieve the web page. Status code: {response.status_code}")

    soup = BeautifulSoup(html_content, "html.parser")
    table = soup.find_all("table")[1]
    trs = table.find_all("tr")

    data = {}
    for i in range(len(trs)):
        if(i <= 2):
            continue
        else:
            city = trs[i].find("a").text if trs[i].find("a") else ""
            time_zone = trs[i].find_all("td")[3].text if (len(trs[i].find_all("td")) > 1) and (trs[i].find_all("td")[3].text != "GMT") else ""
            summer_time = True if trs[i].find("td", attrs={"bgcolor": "#00ee00"}) else False
            if(city != "" and time_zone != ""):
                data["city"] = city
                data["time_zone"] = time_zone.replace(".0", "")
                data["summer_time"] = summer_time
                json.dump(data, json_file, ensure_ascii=False, indent=4)
                if(i == len(trs)-1):
                    json_file.write("\n")
                else:
                    json_file.write(",\n")
    json_file.write("\t]\n}")
