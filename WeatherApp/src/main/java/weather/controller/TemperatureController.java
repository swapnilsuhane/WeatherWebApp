package weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import weather.entity.Weather;
import weather.service.JsonParserServiceImpl;
import weather.service.WeatherServiceImpl;
import weather.validator.WeatherValidator;

@Controller
public class TemperatureController extends WeatherServiceImpl {
	
	@Autowired
	@Qualifier("weatherValidator")
	private WeatherValidator weatherValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(weatherValidator);
	}

	@ModelAttribute("weather")
	public Weather createWeatherModel() {
		// ModelAttribute value should be same as used in the jsp
		return new Weather();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getWeatherPage(Model model) {
		ModelAndView mav = new ModelAndView("weather", "weather", model);
		mav.addObject("weather", new Weather());
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView getTemperatureDetails(
			@ModelAttribute("weather") @Validated Weather weather,
			BindingResult result, @RequestParam String zipCode, Model model) {
		
		if (result.hasErrors())
            return new ModelAndView("weather");
	
		setZipCode(zipCode);

		model.addAttribute("zipCode", getZipCode());
		try {
			String jsonText = getResponse(getConnection());
			
			if (jsonText != "" && jsonText != null) {
				weather = new JsonParserServiceImpl(jsonText).parseJSONText();
			}
			
		} catch (Exception e) {
			model.addAttribute("error", e.toString());
		}
		
		 ModelAndView mav = new ModelAndView("weather", "weather", model);
		 mav.addObject(weather);

		return mav;
	}

}
