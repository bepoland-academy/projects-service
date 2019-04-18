package pl.betse.beontime.projectservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.betse.beontime.projectservice.bo.RoleBo;
import pl.betse.beontime.projectservice.mapper.RoleMapper;
import pl.betse.beontime.projectservice.model.RoleBody;
import pl.betse.beontime.projectservice.service.RoleService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/projects/roles")
public class RolesController {

    private final RoleMapper roleMapper;
    private final RoleService roleService;

    @Value("${api-prefix}")
    private String API_PREFIX;

    public RolesController(RoleMapper roleMapper, RoleService roleService) {
        this.roleMapper = roleMapper;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<Resources<RoleBody>> getListOfAllRoles() {
        List<RoleBody> roles = roleService.allRoles()
                .stream()
                .map(roleMapper::fromBoToBody)
                .collect(Collectors.toList());
        roles.forEach(this::addLinks);
        return ResponseEntity.ok(new Resources<>(roles));
    }

    @GetMapping("/{guid}")
    public ResponseEntity<Resource<RoleBody>> getRoleByGuid(@PathVariable("guid") String roleGuid) {
        RoleBody roleBody = roleMapper.fromBoToBody(roleService.findByGuid(roleGuid));
        addLinks(roleBody);
        return ResponseEntity.ok(new Resource<>(roleBody));
    }

    @PostMapping
    public ResponseEntity createRole(@RequestBody @Valid RoleBody roleBody) {
        RoleBo roleBo = roleService.addNewRole(roleMapper.fromBodyToBo(roleBody));
        return ResponseEntity.ok(roleBo);
    }

    @PutMapping("/{guid}")
    public ResponseEntity updateRole(@PathVariable("guid") String guid, @RequestBody @Valid RoleBody roleBody) {
        roleService.editRole(guid, roleMapper.fromBodyToBo(roleBody));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity deleteRole(@PathVariable("guid") String guid) {
        roleService.deleteRole(guid);
        return ResponseEntity.ok().build();
    }

    private void addLinks(RoleBody roleBody) {
        roleBody.add(linkTo(methodOn(RolesController.class).getRoleByGuid(roleBody.getRoleId())).withSelfRel());
        if (!roleBody.isProjects()) {
            roleBody.add(linkTo(methodOn(RolesController.class).deleteRole(roleBody.getRoleId())).withRel("DELETE"));
        }
    }

}
