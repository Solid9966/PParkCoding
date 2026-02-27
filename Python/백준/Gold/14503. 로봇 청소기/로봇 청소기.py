"""
1. 아이디어
- while, 특정조건 까지
- 4방향을 for문 탐색
- 탐색 불가시, 뒤로 한칸 후진
- 후진 불가능하면 종료
2. 시간복잡도
- O(NM) = O(2500) < 2억, 가능
3. 자료구조
- map = int[][]
- 로봇청소기 위치,방향,청소한곳수
"""

import sys
input = sys.stdin.readline
n,m = map(int,input().split())
y,x,d = map(int,input().split())
map = [list(map(int,input().split())) for _ in range(n)]
cnt = 0
dy = [-1,0,1,0]
dx = [0,1,0,-1]

while 1:
    # 청소가 안되있는 경우에만 청소되게,
    if map[y][x] == 0:
        map[y][x] = 2
        cnt += 1
    sw = False
    # 사방 검문,
    for i in range(1,5):
        # 다음 바라보는 곳,
        ny = y + dy[d-i]
        nx = x + dx[d-i]
        # 맨첨에 백트래킹 결과 값처럼, map 크기가 알맞는지 확인
        if 0<=ny<n and 0<=nx<m:
            # 보는 방향에 청소가 안되있다면,
            if map[ny][nx] == 0:
                # 그 방향으로 회전한다음 한칸 전진후 1번부터 진행.
                d = (d-i+4) % 4
                y = ny; x = nx
                # 1번으로 돌아가기
                sw = True
                break
    # 4방향 모두 없는 경우
    if sw == False:
        # 뒤쪽 방향이 막혀있는지 확인
        ny = y - dy[d]; nx = x - dx[d]
        if 0 <= ny < n and 0 <= nx < m:
            # 벽이면,
            if map[ny][nx] == 1:
                # 종료
                break
            # 벽이 아니면,
            else:
                # 뒤로 후진
                y = ny; x = nx
        # 갈 곳 없는 경우,
        else:
            break

print(cnt)