package com.OrderFlowAPI.OrderFlowAPI.service.classes;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.OrderFlowAPI.OrderFlowAPI.dto.StatusDto;
import com.OrderFlowAPI.OrderFlowAPI.exception.BusinessException;
import com.OrderFlowAPI.OrderFlowAPI.exception.ErrorCode;
import com.OrderFlowAPI.OrderFlowAPI.mapper.StatusMapper;
import com.OrderFlowAPI.OrderFlowAPI.model.UserStatus;
import com.OrderFlowAPI.OrderFlowAPI.repository.IUserStatusRepository;
import com.OrderFlowAPI.OrderFlowAPI.service.IUserStatusService;

@Service
public class UserStatusService implements IUserStatusService {

    private final IUserStatusRepository iUserStatusRepository;

    public UserStatusService(IUserStatusRepository iUserStatusRepository) {
        this.iUserStatusRepository = iUserStatusRepository;
    }

    @Override
    public StatusDto findById(int statusId) {
        UserStatus userStatus = iUserStatusRepository.findById(statusId)
                .orElseThrow(() -> new BusinessException("Status not found with id: " + statusId,
                        ErrorCode.USERSTATUS_NOT_FOUND,
                        HttpStatus.NOT_FOUND));
        return StatusMapper.toDto(userStatus);
    }

}
