#include <string.h>
#include <stdbool.h>
#include "ffmpeg.h"

//保证同时只能一个线程执行
static pthread_mutex_t cmdLock;
static int cmdLockHasInit = 0;
bool hasRegistered = false;


int executeCommand(int64_t handle, const char *command,
                                   void (*callback)(int64_t, int, float),
                                   int64_t totalTime) {
    av_log(NULL, AV_LOG_ERROR, "executeJNI => command = %s", command);

    if (NULL == command) {
        return -1;
    }
    if (!hasRegistered) {
        avformat_network_init();
        hasRegistered = true;
    }
    if (!cmdLockHasInit) {
        pthread_mutex_init(&cmdLock, NULL);//初始化
        cmdLockHasInit = 1;
    }
    pthread_mutex_lock(&cmdLock);

    char *pCommand = (char *) command;
    int stingLen = (int) (strlen(command) + 1);
    char *argv[stingLen];

    char *buffer = NULL;
    int index = 0;
    int isStartYH = 0;
    for (int i = 0; i < stingLen; ++i) {
        char str = *pCommand;
        pCommand++;
        if (NULL == buffer) {
            buffer = malloc(512);
            memset(buffer, 0, 512);
            argv[index++] = buffer;
        }
        //保证引号成对出现
        if (str == '"') {
            if (isStartYH) {
                isStartYH = 0;
            } else {
                isStartYH = 1;
            }
            continue;
        }
        if (str != ' ' || isStartYH) {
            *buffer = str;
            buffer++;
        } else {
            buffer = NULL;
        }
    }
//    for (int i = 0; i < index; ++i) {
//        av_log(NULL, AV_LOG_DEBUG, "cmd=%s", argv[i]);
//    }
    //手动告诉它结束了,防止出现意外
    argv[index] = 0;
    int ret = exe_ffmpeg_cmd(index, argv, handle, callback, totalTime);
    for (int i = 0; i < index; ++i) {
        free(argv[i]);
    }
    pthread_mutex_unlock(&cmdLock);
    return ret;
}

//int executeFFmpegCommand(int64_t handle, const char *command,
//                         void (*progressCallBack)(int64_t, int, float)) {
//    return
//}

int cancelCommand() {
    return cancel_exe_ffmpeg_cmd();
}

