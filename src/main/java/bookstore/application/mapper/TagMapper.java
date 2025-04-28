package bookstore.application.mapper;

import bookstore.application.dto.LibrarianDto;
import bookstore.application.dto.TagDto;
import bookstore.application.entity.Tag;

import java.util.function.Function;

public class TagMapper {
    public static final Function<Tag, TagDto> mapTagToTagDto = TagMapper::mapTagToTagDto;

    public static final Function<TagDto, Tag> mapTagDtoToTag = TagMapper::mapTagDtoToTag;

    private static TagDto mapTagToTagDto(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .description(tag.getDescription())
                .time(tag.getTime())
                .librarian(LibrarianMapper.mapLibrarianToLibrarianDto.apply(tag.getLibrarian()))
                .build();
    }

    private static Tag mapTagDtoToTag(TagDto tagDto) {
        return Tag.builder()
                .id(tagDto.getId())
                .name(tagDto.getName())
                .description(tagDto.getDescription())
                .time(tagDto.getTime())
                .build();
    }
}
