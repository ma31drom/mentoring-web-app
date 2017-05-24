package com.epam.mentoring.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mentoring.model.User;
import com.epam.mentoring.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;

@RestController
public class RestJsonController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/rest/users/{ssoId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseDTO<User> users(@PathVariable String ssoId) {
		ResponseDTO<User> resp = new ResponseDTO<>();
		User findBySSO = userService.findBySSO(ssoId);
		if (findBySSO != null) {
			resp.setResult(findBySSO);
			resp.setSuccess(true);
			return resp;
		}
		throw new IllegalArgumentException("User not found");
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	class ResponseDTO<T> {

		private Boolean success;

		private String message;

		private T result;

		public Boolean getSuccess() {
			return success;
		}

		public void setSuccess(Boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public T getResult() {
			return result;
		}

		public void setResult(T result) {
			this.result = result;
		}
	}

	@ExceptionHandler
	public ResponseDTO<Void> handle(Throwable e) {
		ResponseDTO<Void> dto = new ResponseDTO<>();
		dto.setSuccess(false);
		dto.setMessage(e.getMessage());
		return dto;
	}

}
